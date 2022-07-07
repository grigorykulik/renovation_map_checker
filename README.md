# renovation_map_checker

Simple Selenium-based parser that checks whether your house is included into the renovation program in Saint Petersburg and sends notifications to your Telegram channel. The parser can be packaged into a single executable jar file using maven-assembly-plugin and uploaded to a server where you can schedule its execution using cron. The browser is running in a headless mode, so there's need to use xvfb.

After the jar's been uploaded, you can edit the addresses list directly on the server using a text editor of your choice.
