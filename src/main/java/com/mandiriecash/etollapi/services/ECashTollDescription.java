package com.mandiriecash.etollapi.services;

public class ECashTollDescription {
    private int activityId;
    private int sourceTollId;
    private int destTollId;
    private String plateNo;

    public ECashTollDescription(int activityId,int sourceTollId,int destTollId,String plateNo){
        this.activityId = activityId;
        this.sourceTollId = sourceTollId;
        this.destTollId = destTollId;
        this.plateNo = plateNo;
    }

    public static ECashTollDescription parse(String description){
        String[] split = description.split("-");
        return new ECashTollDescription(
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3]),
                split[4]
        );
    }

    @Override
    public String toString() {
        return String.format("toll-%d-%d-%d-%s",activityId,sourceTollId,destTollId,plateNo);
    }
}
