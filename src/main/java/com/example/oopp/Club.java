package com.example.oopp;

public class Club {
    private String clubId;
    private String clubName;
    private String clubDescription;

    public ClubAdvisor getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(ClubAdvisor clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }

    private ClubAdvisor clubAdvisor;
    public Club() {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    public Club(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setClubId(String clubId) {
    }

    public void setClubDescription(String clubDescription) {
    }

    public void setTeacherId(String teacherId) {
    }
}
