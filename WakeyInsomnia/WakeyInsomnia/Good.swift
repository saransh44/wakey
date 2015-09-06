//
//  Good.swift
//  WakeyInsomnia
//
//  Created by Anand Tyagi on 9/6/15.
//  Copyright (c) 2015 Anand Tyagi. All rights reserved.
//

import UIKit

class Good: UIViewController {
    let gradientLayer = CAGradientLayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // 1
        
        
        self.view.backgroundColor = UIColor.greenColor() //can be set as an image (the angry face)
        // 2
        
        
        // 5
        //self.view.layer.addSublayer(gradientLayer)
        //view.insertSublayer(gradientLayer, below: self.view.subviews[0].layer)
        
        // Do any additional setup after loading the view, typically from a nib.
        
        check(0)
        
    }
    
    func check(m: Int){
        var f = false
        
        print("hello")
        
        if(m == 1){
            f = true
        }
        
        if(m == 0){
            self.performSegueWithIdentifier("G2B", sender: self)

        }
    }

    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
    
    
}

