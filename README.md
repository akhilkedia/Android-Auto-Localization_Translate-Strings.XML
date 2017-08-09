# Android Auto-Localization - Translate Strings.XML

[![LicenseGPLv3](https://img.shields.io/badge/License-GPL%20v3-green.svg)](http://www.gnu.org/licenses/gpl-3.0) [![Donate](https://img.shields.io/badge/Donate-PayPal-blue.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UY6TVJXST724J)

This is a project to localize your Android app , by translating your string resources(e.g. `strings.xml`)  automatically using Microsoft Azure Translate.
It requires Microsoft Azure Translation API key (instructions below).
Helps developers localize their Android app easily to 56 languages, with just one click.

## Features
* Translates Android's Strings.xml files to 56 languages!
* Can also translate small HTML files!
* Cache translations so you dont end up using all your quota everytime
* Handles some XML escaping

## Usage
* Simply edit the constants in `src/main/Const.java`
* Run the java code in Eclipse (Windows/Linux/etc.) - It will generate all possible translations for you


## How to get Microsoft Azure Translate Key

1. Sign up for a Microsoft Azure account.
If you don't already have an Azure account, sign up for a [Microsoft Azure account](http://azure.com).
2. After you have an account, sign into the [Azure Portal](http://portal.azure.com).
3. Add a "Microsoft Translator API Subscription" to your Azure account.
  1. Choose a "pay-as-you-go" subscription. Will require a credit card, but don't worry, nothing will be charged.
  2. Select the "+ New" option.
  3. Select "Intelligence" from the list of services.
  4. Select "Cognitive Services APIs".
  5. Select the "API Type" option.
  6. Select "Text Translation".
  7. In the "Pricing Tier" section, select the "Free Tier (F0)".
  8. Fill out the rest of the form, and press the "Create" button.
4. Retrieve your "Authentication Key".
  1. Go to "All Resources" and select the Microsoft Translator API you subscribed to.
  2. Go to the "Keys" option and copy your subscription key to access the service.

## Limitations
* Does not handle String Arrays so far
* Does not handle CDATA
Keep your strings simple, and this will work just fine. For any issues, post on my GitHub. Or send a pull request.

## Credits
Fork of original code by Süleyman Yılmaz at https://github.com/e1631225

## Donations
If you like this project, buy me a cup of coffee! :) 

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UY6TVJXST724J)

## License
This program is Android Auto-Localization
Copyright (C) 2017  Akhil Kedia

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.