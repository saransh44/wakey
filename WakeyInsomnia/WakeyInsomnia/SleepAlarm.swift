
//
//  SleepAlarm.swift
//  WakeyInsomnia
//
//  Created by Sofia Fernandez on 9/6/15.
//  Copyright (c) 2015 Anand Tyagi. All rights reserved.
//

import UIKit
import AVFoundation

class SleepAlarm:UIViewController {
    //let gradientLayer = CAGradientLayer()
    
    var AudioPlay = AVAudioPlayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.redColor() //can be set as an image (the angry face)
        
        AudioPlay = self.setupAudioPlayerWithFile("Sounds/Tornado", type:"mp3")
        
       AudioPlay.play()
        
        var AlertView = UIAlertController(title: "Insomnia Tips", message: "No Sleeping For You", preferredStyle: UIAlertControllerStyle.Alert)
        
        AlertView.addAction(UIAlertAction(title: "Go", style: UIAlertActionStyle.Default, handler:
            
            nil))
       
       self.performSegueWithIdentifier("BadToGood", sender: self)
        
        
        }
    
    
    
    
     func setupAudioPlayerWithFile(file:NSString, type:NSString) -> AVAudioPlayer  {
            
            var path = NSBundle.mainBundle().pathForResource(file as String, ofType: type as String)
            
            var url = NSURL(fileURLWithPath: path!)
            
        
            var error: NSError?
            
            var audioPlayer:AVAudioPlayer?
            
            audioPlayer = AVAudioPlayer(contentsOfURL: url, error: &error)
            
            return audioPlayer!
        
        
        
            
        }
    
        override func didReceiveMemoryWarning() {
            super.didReceiveMemoryWarning()
            // Dispose of any resources that can be recreated.
        }
}
