package com.kb.jjan.domain.user.service;


import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserRequest;
import com.kb.jjan.domain.user.dto.UserUpdatePriceRequest;
import com.kb.jjan.domain.user.exception.NotFoundFamCode;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyCodeService familyCodeService;

    public void registerUser(UserRequest userRequest) throws Exception {
        User user = userRequest.toEntity();
        if (Objects.equals(user.getIsParent(), "F")) {
            boolean check = familyCodeService.isCodeExists(user.getFamCode());
            if (check) {
                userRepository.save(user);
            } else throw new NotFoundFamCode();
        } userRepository.save(user);
    }

    @Transactional
    public int updateUser(UserUpdatePriceRequest userUpdatePriceRequest) {
        User findUser = userRepository.getReferenceById(userUpdatePriceRequest.getUserId());
        if(findUser != null) {
            int beforeBalance = findUser.getBalance();
            int afterBalance = beforeBalance - userUpdatePriceRequest.getPrice();
            findUser.setBalance(afterBalance);
            return afterBalance;
        }
        return 0;
    }




}
