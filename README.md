# Kdenlive to YouTube 
Java program that renders videos created with Kdenlive and upload them to YouTube

## Purpose
Ask any YouTuber and they will tell you that creating YouTube videos is a time consuming process. 
Somtimes the longest part of the video making process is redendering the video. 

Some video editors, have a version of their software that can be ran on the server to render the video files
created. By offloading the video rendering to another machine, you can edit more videos in less time. 

I've created this Java application that will look for files in a directory and if found, will tar them and 
then render the project. 

This automation requires that you use the Archive Project feature in Kdenlive to properly build the video 
output file. 