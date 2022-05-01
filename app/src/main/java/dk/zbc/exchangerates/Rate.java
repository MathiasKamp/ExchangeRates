package dk.zbc.exchangerates;

/**
 * this class represents a currency rate. it has a name and a spot value
 */
public class Rate {

    private String Name;
    private Double spotRate;

    /**
     * this method returns the Rate's name
     * @return Name the name of the rate
     */
    public String getName() {
        return Name;
    }

    /**
     * this method sets the Rate's name
     * @param name the name of the rate
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * this method returns the spot value
     * @return spotRate the spotRate of the given rate
     */
    public Double getSpotRate() {
        return spotRate;
    }

    /**
     * This method sets the rate's spot value
     * @param spotRate the spot rate of the currency
     */
    public void setSpotRate(Double spotRate) {
        this.spotRate = spotRate;
    }

    /**
     * constructor of Rate. whenever a new Rate is created name and spotRate are required
     * @param name the name of the currency rate. eg. USD
     * @param spotRate the spot rate of the currency rate. eg 7.65
     */
    public Rate(String name, double spotRate) {

        this.Name = name;
        this.spotRate = spotRate;
    }

    public Rate(String name){
        this.Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
