export class ReceivedQuery {
    public diseaseName: string;
    public anatomy: Array<string>;
    public symptoms: Array<string>;
    public para: Array<string>;
    public doc: Array<string>;

    constructor(name, ana, sym, par, docu) {
        this.diseaseName = name;
        this.anatomy = ana;
        this.symptoms = sym;
        this.para = par;
        this.doc = docu;
    }
}
