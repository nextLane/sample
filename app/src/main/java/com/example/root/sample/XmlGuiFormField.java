
package com.example.root.sample;




// class to handle each individual form
public class XmlGuiFormField {
	String name;
	String label;
	String type;
	boolean required;
	String options;
	Object obj;			// holds the ui implementation , i.e. the EditText for example
	
	
	// getters & setters
	//public String getName() {
//		return name;
//	}
	//public void setName(String name) {
	//	this.name = name;
//	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		//sb.append("Field Name: " + this.name + "\n");
		sb.append("Field Label: " + this.label + "\n");
		sb.append("Field Type: " + this.type + "\n");
		sb.append("Required? : " + this.required + "\n");
		sb.append("Options : " + this.options + "\n");
		sb.append("Value : " + (String) this.getData() + "\n");
		
		return sb.toString();
}
	public String getFormattedResult()
	{
		return this.name + "= [" + (String) this.getData() + "]";

	}
	
	public Object getData()
	{
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<thereeeeeeee");
        System.out.println(type);
		if (type.equals("text") || type.equals("numeric"))
		{
			if (obj != null) {
				XmlGuiEditBox b = (XmlGuiEditBox) obj;
				return b.getValue();
			}
		}
		if (type.equals("choice")) {
			if (obj != null) {
				XmlGuiPickOne po = (XmlGuiPickOne) obj;
				return po.getValue();
			}
		}
        if (type.equals("CheckBox")){
            if (obj != null){
                XmlGuiCheckbox pa= (XmlGuiCheckbox) obj;
                return pa.getValue();
            }

        }
        else{
        if(obj !=null){
        System.out.println("xfdgfgdffh");
        XmlGuiCheckbox_m sa = (XmlGuiCheckbox_m) obj;
        System.out.println("hereeeeeeee");
        return sa.getVal();
        }
        }
		
		// todo, add other UI elements here
		return null;
	}

}