# AVATAR Project

<p align="center">
  <img src="https://github.com/regnerus/AVATAR/blob/master/avatar.png" width="300" />
</p>

In the AVATAR project, we develop and demonstrate virtual characters (embodied conversational agents) that can play the role of a police officer or a witness in a police interview. The virtual interview platform for training interview skills integrates the speech recognizer developed in the NLSpraak project, a social agent platform (ASAP) for behavior generation, a dialogue module, text-to-speech generation and virtual humans.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Some prerequisites are needed before installing the project. 

* **Apache ActiveMQ** is an open source messaging server used in communication between the [ASAP realiser](http://asap-project.ewi.utwente.nl/wiki/AsapRealizer) and the Unity AVATAR.

  ActiveMQ can be installed from the Apache website [Apache ActiveMQ Getting Started](http://activemq.apache.org/getting-started.html).

* **Fluency** is a multilingual Text-to-Speech Synthesis platform for Dutch. Fluency is optional and only used for Dutch TTS synthesis, if English TTS synthesis is used, Fluency is *NOT* needed. 

  Fluency can be installed from the Fluency website [Fluency TTS 8.0](https://www.fluency.nl/tts/index.htm).
  
* **MaryTTS** is an open-source, multilingual Text-to-Speech Synthesis platform. MaryTTS should be installed by default on your system. 

  Otherwise MaryTTS can be installed from the MaryTTS website [The MARY Text-to-Speech System Introduction](http://mary.dfki.de/index.html).

### Installing

The AVATAR project can be installed by cloning this repository 

1. Under the repository name, click Clone or download.
2. In the Clone with HTTPs section, click the clipboard icon to copy the clone URL for the repository.
3. Open Terminal.
4. Change the current working directory to the location where you want the cloned directory to be made.
5. Type `git clone`, and then paste the URL you copied in Step 2.

    ``` $ git clone https://github.com/regnerus/AVATAR.git ```
6. Press Enter. Your local clone will be created.
   
   ``` 
   $ git clone https://github.com/regnerus/AVATAR.git
   Cloning into 'AVATAR'...
   remote: Counting objects: 1103, done.
   remote: Compressing objects: 100% (29/29), done.
   remote: Total 1103 (delta 10), reused 0 (delta 0), pack-reused 1066
   Receiving objects: 100% (1103/1103), 10.65 MiB | 2.21 MiB/s, done.
   Resolving deltas: 100% (402/402), done.
   ```
   
## Run Starters
The system consists of two main compontents that connect to each other via Websockets and therefore can be runned on seperate systems.

### Input 
Input can be runned, either as a SpeechToText API or a Wizard Of Oz application.

To run the input, open: `start_input.bat`. A prompt will show up where the language (EN/NL) and input (WOZ/SPEECH) can be selected.

After selecting these options in the prompt the input is running.

### Dialogue Manager and Behaviour Planner 
To run the dialogue manager and behaviour planner, open: `start.bat`. A prompt will show up where the language (EN/NL) and dialog manager (QA) can be selected.

After selecting these options in the prompt the dialogue manager and behaviour planner are running.

### Schematic
<p align="center">
  <img src="https://github.com/regnerus/AVATAR/blob/master/schematic.png" width="500" />
</p>

## Agent Binaries
Agent Binaries are licensed, contact:
* Rieks op den Akker (h.j.a.opdenakker@utwente.nl) or Merijn Bruijnes (m.bruijnes@utwente.nl) for a copy.

Place in `\binary`

Run `\binary\World\CleVR.PoP.Unity.exe`

## Acknowledgments

* https://www.utwente.nl/ewi/hmi/research/projects/avatar/
