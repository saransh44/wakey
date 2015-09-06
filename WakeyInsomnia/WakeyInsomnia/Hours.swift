//
//  Hours.swift
//  WakeyInsomnia
//
//  Created by Anand Tyagi on 9/5/15.
//  Copyright (c) 2015 Anand Tyagi. All rights reserved.
//
//
import Foundation
import UIKit

class Hours : UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    let gradientLayer = CAGradientLayer()
    var pickerData = [Int]()
    @IBOutlet var choice: [UIPickerView]!

    
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
        var i = 0;
        for i = 0; i < pickerData.count; i++ {
            pickerData[i] = i + 1;
    
        }
        
        var label = UILabel(frame: CGRectMake(0, 0, 200, 21))
        label.center = CGPointMake(160, 284)
        label.textAlignment = NSTextAlignment.Center
        //label.text = chosen picker one
        self.view.addSubview(label)
        
        
    }
    
    
      override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    // returns the number of 'columns' to display.
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int{
        return 1
        }
    
    // returns the # of rows in each component..
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int{
    
        return 15
    }
    
     func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String!{
        return String(row + 1)
        
        
    }

   }


