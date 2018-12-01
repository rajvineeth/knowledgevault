import { Paragraph } from './paragraph';

export class ReceivedQuery {
    MedicalCondition: String;
    MedicalSymptoms: String[];
    AnatomicalStructures: String[];
    Paragraphs: Paragraph[];

    constructor(cond, symp, struc, para) {
        this.MedicalCondition = cond;
        this.MedicalSymptoms = symp;
        this.AnatomicalStructures = struc;
        this.Paragraphs = para;
    }
}
