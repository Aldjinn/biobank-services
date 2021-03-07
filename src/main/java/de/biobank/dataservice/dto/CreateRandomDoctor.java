package de.biobank.dataservice.dto;

import lombok.Data;

@Data
public class CreateRandomDoctor {

    private int count;
    private String locale;
    private boolean useBatch;

}
