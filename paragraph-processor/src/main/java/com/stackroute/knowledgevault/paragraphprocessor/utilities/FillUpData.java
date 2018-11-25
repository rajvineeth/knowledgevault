/**
 * @author: jshyperx
 */

package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FillUpData {

    private static final Logger LOGGER = LoggerFactory.getLogger(FillUpData.class);
    private FillUpData() {}

    private static final String JSONTEMPLATE =
            "{" +
            " \"@context\": \"http://schema.org\"," +
            " \"@type\": \"MedicalCondition\"," +
            " \"alternateName\": \"null\"," +
            " \"associatedAnatomy\": {" +
            "   \"@type\": \"AnatomicalStructure\"," +
            "   \"name\": \"null\"" +
            " }," +
            " \"cause\": [" +
            "   {" +
            "     \"@type\": \"MedicalCause\"," +
            "     \"name\": \"null\"" +
            "   }," +
            "   {" +
            "     \"@type\": \"MedicalCause\"," +
            "     \"name\": \"null\"" +
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
            "       \"name\": \"null\"" +
            "     }," +
            "     {" +
            "       \"@type\": \"MedicalSymptom\"," +
            "       \"name\": \"null\"" +
            "     }," +
            "     {" +
            "       \"@type\": \"MedicalSymptom\"," +
            "       \"name\": \"null\"" +
            "     }" +
            "   ]" +
            "" +
            " }," +
            " \"possibleTreatment\":[" +
            "   {\"@type\":\"Drug\"," +
            "     \"name\":\"null\"," +
            "     \"DoseSchedule\":[" +
            "       {\"doseUnit\":\"null\"}," +
            "       {\"frequency\":\"null\"}" +
            "     ]" +
            "   }," +
            "   {\"@type\":\"Drug\"," +
            "     \"name\":\"null\"," +
            "     \"DoseSchedule\":[" +
            "       {\"doseUnit\":\"null\"}," +
            "       {\"frequency\":\"null\"}" +
            "     ]" +
            "   }" +
            " ]" +
            "}";

    public static JSONObject fillOntology(Map<String,List<Pair>> tags) {

        JSONObject obj = null;
        try {
            obj = new JSONObject(JSONTEMPLATE);
        } catch (JSONException e) {
            LOGGER.debug(String.valueOf(e.getStackTrace()));
        }

        for(Map.Entry<String,List<Pair>> entry: tags.entrySet()) {

            Collections.sort(entry.getValue(),(p1,p2) -> (int) (p1.getScore() - p2.getScore()));

            if(entry.getKey().compareTo("diseases")==0 && !entry.getValue().isEmpty()) {
                try {
                    obj.put("alternateName",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    LOGGER.debug(String.valueOf(e.getStackTrace()));
                }
            }
            else if(entry.getKey().compareTo("body-part")==0 && !entry.getValue().isEmpty()) {
                try {
                    JSONObject bp = (JSONObject) obj.get("associatedAnatomy");
                    bp.put("name",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    LOGGER.debug(e.getMessage());
                }
            }
            else if(entry.getKey().compareTo("symptom")==0 && !entry.getValue().isEmpty()) {
                try {
                    JSONObject dd = (JSONObject) obj.get("differentialDiagnosis");
                    JSONArray ds = (JSONArray) dd.get("distinguishingSign");
                    JSONObject jobj = ds.getJSONObject(0);
                    jobj.put("name",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    LOGGER.debug(String.valueOf(e.getStackTrace()));
                }
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(obj);
        LOGGER.info(jsonOutput);

        LOGGER.info("successfully updated JSON");
        return obj;
    }
}
