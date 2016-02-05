package com.mandiriecash.etollapi.mea;

import java.io.IOException;

public class MEAIOException extends IOException {
    public MEAIOException(IOException e){
        super(e);
    }
}
