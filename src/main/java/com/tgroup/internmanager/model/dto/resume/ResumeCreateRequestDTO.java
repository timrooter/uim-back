package com.tgroup.internmanager.model.dto.resume;

import lombok.Data;

@Data
public class ResumeCreateRequestDTO {
    private String title;
    private String summary;
    private String skills;
    private String education;
    private String gender;
    private String phone;
    private String socialMedia;
    private String location;
    private String birthday;
    private String portfolioLink;
}
