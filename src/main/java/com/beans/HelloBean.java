package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
    private String soname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String validatePage(){
        Pattern p = Pattern.compile("^[A-Za-z]{3,15}$");
        Matcher m1 = p.matcher(name);
        Matcher m2 = p.matcher(soname);
        if( m1.matches()&& m2.matches()) {
            return "success";
        }else {
            return "fail";
        }
    }


    public String getSoname() {
        return soname;
    }

    public void setSoname(String name) {
        this.soname = name;
    }

}