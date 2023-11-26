package interfaces.services;

import enums.GenerateType;

import models.Camp;


/**
 * The {@code GenerateReportServiceable} interface defines the functionality for generating reports related to a camp.
 * It extends the {@link Exportable} interface to support exporting the generated reports.
 * 
 * <p>Classes that implement this interface should provide their own implementation for generating reports based on a specified {@link Camp} and {@link GenerateType}.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface GenerateReportServiceable extends Exportable {
	
    /**
     * Generates a report for the specified camp based on the given type.
     * 
     * @param camp The camp for which the report is generated.
     * @param type The type of report to generate (e.g., PDF, Excel).
     * @return A string representing the generated report.
     */
    public String generate(Camp camp, GenerateType type);
}
