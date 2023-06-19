package com.cm.bbuserapi.cron;

import com.cm.bbuserapi.client.RandomUserClient;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.dto.randomData.RandomUser;
import com.cm.bbuserapi.dto.randomData.RandomUserResponse;
import com.cm.bbuserapi.model.User;
import com.cm.bbuserapi.repository.UserRepo;
import com.cm.bbuserapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCron {

    private final UserService userService;
    private final UserRepo userRepo;
    private final RandomUserClient client;

    @Scheduled(fixedRate = 50000)
    public void registerUser() {
        User lastUser = userRepo.findTopByOrderByIdDesc().orElse(null);
        RandomUserResponse response = client.getUser();
        RandomUser randomUser = response.getResults().get(0);
        String name = randomUser.getName().getFirst() + " " + randomUser.getName().getLast();
        String phone = randomUser.getCell();
        phone = phone.replaceAll("[^0-9]", "");
        String userName= randomUser.getName().getFirst() + "@tradetune";
        String password = randomUser.getName().getFirst() + "@sanghi";


        UserDto userDto=new UserDto();
        userDto.setUserName(userName);
        userDto.setName(name);
        userDto.setEmail(randomUser.getEmail());
        userDto.setPhone(phone);
        userDto.setPassword(password);
        userDto.setProfilePic(randomUser.getPicture().getThumbnail());
        userDto.setImgUrl(randomUser.getPicture().getLarge());


        //    log.info("UserRobo: registerUser: userDto: " + userDto);
        userService.register(userDto);
    }
}
