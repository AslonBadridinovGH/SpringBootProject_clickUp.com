package uz.pdp.springbootprojectprocesses.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;
import uz.pdp.springbootprojectprocesses.entity.enums.SystemRoleName;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.RegisterDto;
import uz.pdp.springbootprojectprocesses.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" not found"));
    }

    public ApiResponse registerUser(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) return new ApiResponse("this email was not found",false);
        User user =new User(
                registerDto.getFullName(),registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_ROLE_USER);

        int code = new Random().nextInt(999999); // number of Random multi to 999999
        user.setEmailCode(String.valueOf(code).substring(0,4));
        userRepository.save(user);
        sendEMail(user.getEmailCode(), user.getEmail());
        return new ApiResponse("User was saved",true);
    }

    // through SimpleMailMessage sends confirm link to email of user
    public void sendEMail(String emailCode, String sendingEmail){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("test@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("confirm account");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
        }catch (Exception ignored){

        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())){
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("account is active",true);
            }
            return new ApiResponse("code error",false);
        }
        return new ApiResponse("such User is not exist",false);
    }

}
