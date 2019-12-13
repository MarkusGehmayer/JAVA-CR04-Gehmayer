package cr04;

public class Products {
    private String prodTitle;
    private String prodQuant;
    private String prodDesc;
    private Double oldPr;
    private Double newPr;
    private String imgPath;

    public void setimgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getimgPath() {
        return imgPath;
    }

    public Products(String productsTitel, String prodQuant, String prodDesc, double oldPr, double newPr, String imgPath){
        this.prodTitle = productsTitel;
        this.prodQuant = prodQuant;
        this.prodDesc = prodDesc;
        this.oldPr = oldPr;
        this.newPr = newPr;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "|::| " + prodTitle + ":  old: " + oldPr + " / new: " + newPr;
    }

    public String getprodTitle() {
        return prodTitle;
    }

    public String getprodQuant() {
        return prodQuant;
    }

    public String getprodDesc() {
        return prodDesc;
    }

    public Double getoldPr() {
        return oldPr;
    }

    public Double getnewPr() {
        return newPr;
    }

    public void setnewPr(Double newPr) {
        this.newPr = newPr;
    }

    public void setoldPr(Double oldPr) {
        this.oldPr = oldPr;
    }
}

