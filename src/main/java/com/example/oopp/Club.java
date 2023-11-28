package com.example.oopp;

import java.util.List;


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


    public Club(String clubName) {
            this.clubName = clubName;
        }
        public String getClubName() {
            return clubName;
        }
        public void setClubName(String clubName) {
            this.clubName = clubName;
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

