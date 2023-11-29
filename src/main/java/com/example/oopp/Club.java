package com.example.oopp;

public class Club {
    private String clubId;
    private String clubName;


    public Club(String clubName){

    }


    public Club(String clubId, String clubName, String clubDescription) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    public Club(String clubName, String clubDescription, ClubAdvisor clubAdvisor) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubAdvisor = clubAdvisor;
    }

    public Club(String clubName, String clubDescription) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    public String getClubDescription() {
        return clubDescription;
    }



    private String clubDescription;




    public String getClubName() {
        return null;

//    public ClubAdvisor getClubAdvisor() {
//        return clubAdvisor;
//    }



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
        this.clubDescription = clubDescription;
    }

    public void setTeacherId(String teacherId) {

    }

}
