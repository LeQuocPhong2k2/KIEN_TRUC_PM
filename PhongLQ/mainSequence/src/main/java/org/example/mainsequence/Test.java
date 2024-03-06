package org.example.mainsequence;

import jdepend.xmlui.JDepend;

import java.io.IOException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        JDepend depend =new JDepend(new PrintWriter("report.xml"));
        depend.addDirectory("T:\\PhongLQ");
        depend.analyze();
        System.out.println("DONE");
    }
}
