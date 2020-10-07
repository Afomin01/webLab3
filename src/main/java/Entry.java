public class Entry {
    private double x;
    private double y;
    private double r;
    private boolean result;

    public Entry() {
    }

    public void check(){
        if(x>0 && y>0) result=false;
        else if(x<0 && y>0) result = y < 2*x+r;
        else if(x<0 && y<0) result = x*x+y*y<r;
        else if(x>0 && y<0)result = x<r/2 && y<-r;
        else if(x==0) result = y<r && y>-r;
        else if(y==0) result=x<r/2&&x>-r;
        else result=false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
