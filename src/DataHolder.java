
public class DataHolder {
protected int pin,mode;
public DataHolder(int pin, int mode) {
	super();
	this.pin = pin;
	this.mode = mode;
}
public int getPin() {
	return pin;
}

public void setPin(int pin) {
	this.pin = pin;
}

public int getMode() {
	return mode;
}

public void setMode(int mode) {
	this.mode = mode;
}

@Override
public String toString() {
   return "{\"dataHolder\":{\"pin\":"+pin+",\"mode\":"+mode+"}}";
}
}
