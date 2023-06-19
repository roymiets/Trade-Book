package com.cm.bbuserapi.dto.randomData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RandomUserResponse {
    List<RandomUser> results;
}
