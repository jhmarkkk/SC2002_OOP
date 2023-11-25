package interfaces.services;

import enums.GenerateType;

import models.Camp;

public interface GenerateReportServiceable extends Exportable {
	
    public String generate(Camp camp, GenerateType type);
}
