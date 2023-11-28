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


    public String getClubDescription() {
        return clubDescription;
    }



    private String clubDescription;



    public String getClubName() {
        return null;

//    public ClubAdvisor getClubAdvisor() {
//        return clubAdvisor;
//    }


    }
}
