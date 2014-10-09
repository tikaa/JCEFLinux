/*
* Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tests.simple;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.wso2.devstudio.splash.DevSSplashScreen;


public class MainFrame extends JFrame {
  private static final long serialVersionUID = -5570653778104813836L;
  private final JTextField address_;
  private final CefApp     cefApp_;
  private final CefClient  client_;
  private final CefBrowser browser_;
  private final Component  browerUI_;
 
  private MainFrame(String startURL, boolean useOSR, boolean isTransparent) {
   
    cefApp_ = CefApp.getInstance();    
    client_ = cefApp_.createClient();
    browser_ = client_.createBrowser(startURL, useOSR, isTransparent);
    browerUI_ = browser_.getUIComponent();

    address_ = new JTextField(startURL, 100);
    address_.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        browser_.loadURL(address_.getText());
      }
    });
    getContentPane().add(browerUI_, BorderLayout.CENTER);
    pack();
    setSize(800,600);
    setVisible(true);
     
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
        cefApp_.dispose();
      }
    });
  }

  public static void main(String[] args) {
    /*have to add codenvy web application URL*/
	DevSSplashScreen.StartUp();
        new MainFrame("http://www.wso2.com", OS.isLinux(), false);

  }
}
