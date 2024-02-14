package cl.gendigital.gendeporte.users.core.adapters.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl AbstractService<User, UserForm> implements UserService {

    @Value("${app.links.change-password}")
    private String changePasswordLink;

    @Value("${app.links.email-validation}")
    private String emailValidarionLink;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private EmailService emailService;

    @Value("${app.default.new-pwd-expires-in}")
    private Integer newPwdExpiresIn;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<User> findByUsername(String username) {
        return createServiceResult(
                userRepository.findByUsername(username)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, username)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, username)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<User> findByEmail(String email) {
        return createServiceResult(
                userRepository.findByEmail(email)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, email)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, email)
        );
    }

    @Override
    @Transactional
    public ServiceResult<User> save(UserForm form) {
        String confirmationCode = UUID.randomUUID().toString();
        User newUser = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .validationCode(confirmationCode)
                .build();
        User save = userRepository.save(newUser);
        emailService.sendSimpleMessage(save.getEmail(), newUserConfirmationSubject(save.getUsername()),
                newUserConfirmationText(save.getUsername(), confirmationCode, form.getPassword()));
        return createServiceResultOk(save
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    private String newUserConfirmationText(String username, String confirmationCode, String password) {
        String link = String.format(emailValidarionLink, confirmationCode);
        return messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_MESSAGE, MessageUtils.DEFAULT_OFFSET, username, link, confirmationCode,
                password);
    }

    private String newUserConfirmationSubject(String username) {
        return messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_SUBJECT, MessageUtils.DEFAULT_OFFSET, username);
    }

    @Override
    @Transactional
    public ServiceResult<User> update(User user, UserForm form) {
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        changePassword(user, form);
        return createServiceResultOk(
                userRepository.save(user)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    private void changePassword(User user, UserForm form) {
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            user.setLastPassword(user.getPassword());
            user.setPasswordResetAt(LocalDateTime.now().plusDays(newPwdExpiresIn));
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        } else {
            //TODO throw exception
        }
    }

    @Override
    @Transactional
    public ServiceResult<User> saveUserFirebase(UserFireBaseForm userFireBaseForm) {
        return createServiceResultOk(
                userRepository.save(
                        User.builder()
                                .email(userFireBaseForm.getEmail())
                                .password(passwordEncoder.encode(userFireBaseForm.getPassword()))
                                .passwordResetAt(LocalDateTime.now().plusDays(newPwdExpiresIn))
                                .username(userFireBaseForm.getUsername())
                                .build()
                )
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    @Override
    public ServiceResult<User> changePassword(String validationCode, String newPassword) {
        Optional<User> byValidationCode = userRepository.findByValidationCode(validationCode);

        if (byValidationCode.isPresent()) {
            User user = byValidationCode.get();
            user.setLastPassword(user.getPassword());
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPasswordResetAt(LocalDateTime.now().plusDays(newPwdExpiresIn));
            user.setValidationCode(null);
            userRepository.save(user);

            String subject = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_SUBJECT, MessageUtils.DEFAULT_OFFSET, user.getUsername());
            String message = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_MESSAGE, MessageUtils.DEFAULT_OFFSET);
            emailService.sendSimpleMessage(user.getEmail(), subject, message);
        }

        return createServiceResult(
                byValidationCode
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, validationCode)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, validationCode)
        );
    }

    @Override
    public ServiceResult<User> requestChangePassword(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            String validationCode = UUID.randomUUID().toString();
            User user = byEmail.get();
            String link = String.format(changePasswordLink, validationCode);
            String subject = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_SUBJECT, MessageUtils.DEFAULT_OFFSET, user.getUsername());
            String message = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_MESSAGE, MessageUtils.DEFAULT_OFFSET, link, validationCode);
            emailService.sendSimpleMessage(user.getEmail(), subject, message);
            user.setValidationCode(validationCode);
            userRepository.save(user);
        }

        return createServiceResult(
                byEmail
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, email)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, email)
        );
    }

    @Override
    public ServiceResult<User> emailValidation(String emailToValidate, String validationCode) {
        Optional<User> byEmail = userRepository.findByEmail(emailToValidate)
                .filter(email -> validationCode.equals(email.getValidationCode()));
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            user.setEnabledAt(LocalDateTime.now());
            user.setValidationCode(null);
            user.setPasswordResetAt(LocalDateTime.now());
            userRepository.save(user);

            String subject = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_SUBJECT, MessageUtils.DEFAULT_OFFSET, user.getUsername());
            String message = messageUtils.getMessage(MessageUtils.CODE_MESSAGE_EMAIL_MESSAGE, MessageUtils.DEFAULT_OFFSET, user.getUsername());
            emailService.sendSimpleMessage(user.getEmail(), subject, message);
        }

        return createServiceResult(
                byEmail
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, emailToValidate)
                , messageUtils.getMessage(MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, emailToValidate)
        );
    }
}