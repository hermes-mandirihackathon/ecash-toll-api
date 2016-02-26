package com.mandiriecash.etollapi.services;

/**
 * Created by yafi on 27-Feb-16.
 */
public class ECashTollGTODescription {
    private int sourceTollId;
    private int destTollId;

    public ECashTollGTODescription(int sourceTollId,int destTollId){
        this.sourceTollId = sourceTollId;
        this.destTollId = destTollId;
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
        return String.format("toll_gto-%d-%d",sourceTollId,destTollId);
    }
}
