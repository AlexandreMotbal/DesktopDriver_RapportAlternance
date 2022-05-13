//
//  main.swift
//  control_lib
//
//  Created by Agilitest on 31/01/2022.
//

import CoreGraphics
import ApplicationServices
import Foundation

var order:String = "sans ordre"
print(CommandLine.arguments[0])
if (CommandLine.arguments.count>=1){
    order = CommandLine.arguments[1]
}
if (order == "close") {
    close(target: CommandLine.arguments[2])
}
if (order == "resize") {
    resize(target: CommandLine.arguments[2],height: CommandLine.arguments[3], width: CommandLine.arguments[4])
}
if (order == "getwindowlist"){
    getWindowList()
}
if (order == "getVersion"){
    getVersion(target: CommandLine.arguments[2])
}
if (order == "move"){
	move(target: CommandLine.arguments[2],x: CommandLine.arguments[3], y: CommandLine.arguments[4])
}

func close(target: String){
    let source = """
                           tell application "\(target)"
                               quit
                           end tell
    """
    var error: NSDictionary?
    if let scriptObject = NSAppleScript(source: source){
        let output: NSAppleEventDescriptor = scriptObject.executeAndReturnError(&error)
        if (error != nil){
                print("error : \(error!)")
            
        }
    }
}
func move(target:String, x:String, y:String){
    let source =
"""
tell application "\(target)"
    set bounds of front window to {\(x),\(y),0,0}
end tell
"""
    
    var error: NSDictionary?
    if let scriptObject = NSAppleScript(source: source){
        let output: NSAppleEventDescriptor = scriptObject.executeAndReturnError(&error)
        if (error != nil){
                print("error : \(error!)")
            
        }
    }
        
}
func resize(target:String, height:String, width:String){
    let source =
"""
tell application "\(target)"
    set bounds of front window to {0,0,\(height),\(width)}
end tell
"""
    
    var error: NSDictionary?
    if let scriptObject = NSAppleScript(source: source){
        let output: NSAppleEventDescriptor = scriptObject.executeAndReturnError(&error)
        if (error != nil){
                print("error : \(error!)")
            
        }
    }
        
}
func getVersion(target:String){
    let source =
"""
    get version of application "\(target)"
"""
    var error: NSDictionary?
    if let scriptObject = NSAppleScript(source: source){
        let output: NSAppleEventDescriptor = scriptObject.executeAndReturnError(&error)
        if (error != nil){
                print("error : \(error!)")
            
        }
    }

}


func getWindowList(){
    // list [name] [pid]
    var windowList = [String]()
    if let info = CGWindowListCopyWindowInfo(.optionOnScreenOnly, kCGNullWindowID) as? [[ String : Any]] {
        for dict in info {
            //print(dict)
            let owner = dict["kCGWindowOwnerName"] as? String
            //print(dict["kCGWindowOwnerPID"])
            let pid = dict["kCGWindowOwnerPID"] as! NSNumber
            windowList.append(owner!)
            windowList.append(pid.stringValue)
        }
    }
    for i in stride(from: 0, to: windowList.count, by:2) {
        print(windowList[i])
        print(windowList[i+1])
    }
}
func getSize(name:String)->Any{
    let targetSize = "not find"
    if let info = CGWindowListCopyWindowInfo(.optionOnScreenOnly, kCGNullWindowID) as? [[ String : Any]] {
        for dict in info {
            
            if let windowName = dict["kCGWindowOwnerName"] as? String, windowName == name {
                let test: CFDictionary = dict["kCGWindowBounds"] as! CFDictionary
                if let windowRect = CGRect.init(dictionaryRepresentation: test) {
                    print(windowRect)
//                  Plusieurs variables peuvent être obtenues grâce aux windowRect
//                  windowRect.origin.x || windowRect.origin.y || windowRect.width || windowRect.height
                }
            }
        }
    }
    return targetSize
}



