package com.stackroute.knowledgevault.documentidentifier.loaders;

import com.stackroute.knowledgevault.documentidentifier.services.DocumentServiceImpl;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultData implements ApplicationListener<ContextRefreshedEvent> {

    private DocumentServiceImpl documentService;
    @Autowired
    public DefaultData(DocumentServiceImpl documentService) {
        this.documentService=documentService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        documentService.saveDocuments(new ExtractedFileData(100,"meta","https://www.catie.ca/ga-pdf.php?file=pdf/Brochures/HIV-basics.pdf"));
        documentService.saveDocuments(new ExtractedFileData(101,"meta","http://www.who.int/ith/ITH_chapter_7.pdf"));
        documentService.saveDocuments(new ExtractedFileData(102,"meta","https://www.thoracic.org/patients/patient-resources/resources/tuberculosis.pdf"));
        documentService.saveDocuments(new ExtractedFileData(103,"meta","https://www.acponline.org/system/files/documents/about_acp/chapters/in/16mtg/hemly_diabetes.pdf"));
        documentService.saveDocuments(new ExtractedFileData(104,"meta","https://www.cdc.gov/dengue/resources/denguedhf-information-for-health-care-practitioners_2009.pdf"));
        documentService.saveDocuments(new ExtractedFileData(105,"meta","https://www.heart.org/-/media/data-import/downloadables/pe-abh-what-is-a-heart-attack-ucm_300314.pdf"));
        documentService.saveDocuments(new ExtractedFileData(106,"meta","\n" +
                "http://www.wpro.who.int/philippines/typhoon_haiyan/media/Typhoid_fever.pdf"));
        documentService.saveDocuments(new ExtractedFileData(107,"meta","http://applications.emro.who.int/dsaf/emropub_2011_1306.pdf?ua=1"));
        documentService.saveDocuments(new ExtractedFileData(108,"meta","https://www.aafp.org/afp/2011/1215/p1390.pdf"));
        documentService.saveDocuments(new ExtractedFileData(109,"meta","https://www.laserprofessionals.com/facts/Conjunctivitis.pdf"));
    }
}
