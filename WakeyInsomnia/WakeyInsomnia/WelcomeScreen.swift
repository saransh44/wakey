//
//  ViewController.swift
//  WakeyInsomnia
//
//  Created by Anand Tyagi on 9/5/15.
//  Copyright (c) 2015 Anand Tyagi. All rights reserved.
//

import UIKit

class WelcomeScreen: UIViewController {
    let gradientLayer = CAGradientLayer()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // 1
        self.view.backgroundColor = UIColor.greenColor()
        
        // 2
        gradientLayer.frame = self.view.bounds
        
        // 3
        let color1 = UIColor.redColor().CGColor as CGColorRef
        let color2 = UIColor.orangeColor().CGColor as CGColorRef
        let color3 = UIColor.yellowColor().CGColor as CGColorRef
        
        gradientLayer.colors = [color1, color2, color3]
        
        // 4
        gradientLayer.locations = [0.0, 0.25, 0.75]
        
        // 5
        //self.view.layer.addSublayer(gradientLayer)
        //view.insertSublayer(gradientLayer, below: self.view.subviews[0].layer)
        self.view.layer.insertSublayer(gradientLayer, below: self.view.subviews[0].layer)
        // Do any additional setup after loading the view, typically from a nib.
        
        
    }
    
    
    @IBAction func Begin(sender: AnyObject) {
        self.performSegueWithIdentifier("W2T", sender: self)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    


}

