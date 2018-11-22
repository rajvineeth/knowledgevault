/**
 * @author: jshyperx
 */

package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FillUpData {

    private static final Logger LOGGER = LoggerFactory.getLogger(FillUpData.class);

    public static JSONObject fill(Map<String,String> taggedKeywords) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(
                    "{" +
                            " \"@context\": \"http://schema.org\"," +
                            " \"@type\": \"MedicalCondition\"," +
                            " \"alternateName\": \"angina pectoris\"," +
                            " \"associatedAnatomy\": {" +
                            "   \"@type\": \"AnatomicalStructure\"," +
                            "   \"name\": \"heart\"" +
                            " }," +
                            " \"cause\": [" +
                            "   {" +
                            "     \"@type\": \"MedicalCause\"," +
                            "     \"name\": \"atherosclerosis\"" +
                            "   }," +
                            "   {" +
                            "     \"@type\": \"MedicalCause\"," +
                            "     \"name\": \"spasms of the epicardial artery\"" +
                            "   }" +
                            " ]," +
                            " \"code\": {" +
                            "   \"@type\": \"MedicalCode\"," +
                            "   \"code\": \"413\"," +
                            "   \"codingSystem\": \"ICD-9\"" +
                            " }," +
                            " \"differentialDiagnosis\": {" +
                            "   \"distinguishingSign\": [" +
                            "     {" +
                            "       \"@type\": \"MedicalSymptom\"," +
                            "       \"name\": \"chest pain lasting at least 10 minutes at rest\"" +
                            "     }," +
                            "     {" +
                            "       \"@type\": \"MedicalSymptom\"," +
                            "       \"name\": \"repeated episodes of chest pain at rest lasting 5 or more minutes\"" +
                            "     }," +
                            "     {" +
                            "       \"@type\": \"MedicalSymptom\"," +
                            "       \"name\": \"an accelerating pattern of chest discomfort (episodes that are more frequent, severe, longer in duration, and precipitated by minimal exertion)\"" +
                            "     }" +
                            "   ]" +
                            "" +
                            " }," +
                            " \"possibleTreatment\":[" +
                            "   {\"@type\":\"Drug\"," +
                            "     \"name\":\"Cavlam-650\"," +
                            "     \"DoseSchedule\":[" +
                            "       {\"doseUnit\":\"650mg\"}," +
                            "       {\"frequency\":\"thrice daily\"}" +
                            "     ]" +
                            "   }," +
                            "   {\"@type\":\"Drug\"," +
                            "     \"name\":\"Paracetamol\"," +
                            "     \"DoseSchedule\":[" +
                            "       {\"doseUnit\":\"500mg\"}," +
                            "       {\"frequency\":\"twice daily\"}" +
                            "     ]" +
                            "   }" +
                            " ]" +
                            "}"
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String,String> entry : taggedKeywords.entrySet()) {
            if(entry.getValue().compareTo("diseases")==0) {
                try {
                    obj.put("alternateName",entry.getKey());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(entry.getValue().compareTo("body-part")==0) {
                try {
                    JSONObject bp = (JSONObject) obj.get("associatedAnatomy");
                    bp.put("name",entry.getKey());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                continue;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(obj);
        LOGGER.info(jsonOutput);

        LOGGER.info("successfully updated JSON");
        return obj;
    }
}
