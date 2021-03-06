# Openflorian Alarm Workflow System

## Description
Openflorian is a alarm workflow system that transforms telefax messages sent from alarm coordination center to local rescue departments into web content to display on screens in rescue car halls.

## Licence

Copyright (C) 2015  Bastian Kraus

Openflorian is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version)
    
Openflorian is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
    
You should have received a copy of the GNU General Public License
along with Openflorian.  If not, see <http://www.gnu.org/licenses/>.

## Architecture
Openflorian is a Java based Servlet 3.0 Web Application with a Vert.X Reactive Core. It runs on Servlet 3.0 Containers and utilizes Tesseract to transform telefax files received from telefax daemon into readable text and parses it with regular expressions to display the informations on interactive html pages.

## Technology Openflorian runs on

### Server
* Hardware: runs best on Raspberry PI 3 Model B
* Operating System: Raspbian Linux (https://www.raspbian.org/)
* Database: MySQL 5.x
* Application Server: Apache Tomcat 8.x (http://tomcat.apache.org/tomcat-8.0-doc/index.html)
* UI Framework: Vaadin (https://vaadin.com/home)
* Reactive Core: Vert.X (http://vertx.io/)
* OCR Framework: Tesseract (https://github.com/tesseract-ocr/tesseract)
* Telefax Daemon: Hylafax (http://www.hylafax.org/content/Main_Page)

### Viewer
* Hardware: runs best on Raspberry PI 3 Model B
* Operating System: Raspbian Linux (https://www.raspbian.org/)
* Display Manager: LightDM
* Browser: Iceweasel/Firefox

## Installation Manuals

* [Server Installation](install-server.md)
* [Viewer Installation](install-viewer.md)

## OpenFlorian REST API Doc

* [REST API HTML](apidoc/generated/api-doc.html)
* [REST API JSON](apidoc/generated/swagger-ui/swagger.json)

## Additional Projects and Tools
To give you additional experience and tooling for OpenFlorian, I'm developing additional software tools you may find here:

### OpenFlorian Trigger
Using the f-pro.de USB Buzzer and a little peace of java software to access the Operation Reset REST API Resource of OpenFlorian (http://www.f-pro.de/buzzer/). The Buzzer is attached as USB Keyboard and available as linux device under /dev. OpenFlorian-Trigger is a Java and Vert.X based daemon watching the device and in case of someones pushing the button an event is triggered and fires a call to a defined REST resource endpoint.

See: [OpenFlorian-Trigger](https://bitbucket.org/ceth/openflorian-trigger)