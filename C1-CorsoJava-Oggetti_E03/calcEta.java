class Percentuale{

    private double value;
    private boolean valid;

    public Percentuale(double val){
        setValue(val);
    }

    public double getValue(){
        return this.value;
    }

    public boolean isValid(){
        return this.valid;
    }

    public void setValue(double val){
        if(val>=-100 && val <= 100){
            this.value=val;
            this.valid=true;
        } else{
            this.value=0;
            this.valid=false;
        }
    }

}
class Eta{

    private int value;
    private boolean valid;

    public Eta(int eta){
        setValue(eta);
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int anni){
        if(anni>0){
            this.value=anni;
            this.valid=true;
        }else{
            this.value=0;
            this.valid=false;
        }
    }

    public boolean isValid(){
        return this.valid;
    }

}


class CalcolatoreInvestimento{
    
    private double capitaleIniziale;
    private Percentuale tasso;
    private Eta anni;
    private double[] capitale;
    private double[] rendimenti;
    
    public double[] getCapitale(){
        return this.capitale;
    }
    public double getCapitale(int index){
        if(this.capitale!=null && this.capitale.length>index)
            return this.capitale[index];
        return 0;
    }

    public double[] getRentimenti(){
        return this.rendimenti;
    }
    public double getRentimenti(int index){
        if(this.rendimenti!=null && this.rendimenti.length>index)
            return this.rendimenti[index];
        return 0;
    }

    public CalcolatoreInvestimento(){
        this.capitaleIniziale=0.0;
        this.tasso= new Percentuale(0.0);
        this.setAnni(new Eta(1));
    }

    public CalcolatoreInvestimento(double capitaleIniziale, Percentuale tasso, Eta anni){
        this.capitaleIniziale=capitaleIniziale;
        this.tasso=tasso;
        this.setAnni(anni);
    }

    private void calcolaRendimenti(){
        if(this.anni.isValid() && this.capitaleIniziale>0 && this.tasso.isValid()){
            
            this.capitale[0] = this.capitaleIniziale;
            for(int x=0;x<this.anni.getValue();x++){
                this.rendimenti[x] = this.rendimentoAnnuale(capitale[x]);
                if( x < (this.capitale.length-1)){
                    this.capitale[x+1] = this.capitale[x] + this.rendimenti[x];
                }
            }
        }
    }

    public double rendimentoFinale(){ 
        double  rendimento=0.0;
        for(int y=0; y<this.rendimenti.length;y++){
            rendimento += this.rendimenti[y];
        }
        return rendimento;
    }

    private double rendimentoAnnuale(double capitale){
        if(this.tasso.isValid()){
            return capitale / 100 * this.tasso.getValue();
        } 
        return 0;
    }

    public double getCapitaleIniziale(){
        return this.capitaleIniziale;
    }
    public void setCapitaleIniziale(double capitale){
        this.capitaleIniziale=capitale;
        this.calcolaRendimenti();
    }

    public Percentuale getTasso(){
        return this.tasso;
    }
    
    public void setTasso(Percentuale tasso){
        this.tasso=tasso;
        this.calcolaRendimenti();
    }

    public Eta getAnni(){
        return this.anni;
    }

    public void setAnni(Eta anni){
        this.anni=anni;
        capitale=new double[this.anni.getValue()];
        rendimenti=new double[this.anni.getValue()];
        this.calcolaRendimenti();
    }
}
