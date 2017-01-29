# Android Auto-Localization - Translate Strings.XML
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)

This is a project to localize your Android app , by translating your string resources(e.g. `strings.xml`)  automatically using Microsoft Azure Translate.
It requires Microsoft Azure Translation API key (instructions below).
Helps developers localize their Android app easily to 56 languages, with just one click.

##Features
* Translates to 56 languages
* Cache translations so you dont end up using all your quota everytime
* Handles some XML escaping

##Usage
* Simply edit the constants in `src/main/Const.java`
* Run the java code (Windows/Linux/etc.) - It will generate all possible translations for you

##How to get FREE Microsoft Azure Translation Key
1. Sign into Microsoft Azure account.
If you don’t already have an Azure account, sign up for a Microsoft Azure account at http://azure.com.
2. Subscribe to a "pay-as-you-go" subscription at https://account.windowsazure.com/Subscriptions
Will require a credit card, but don't worry, nothing will be charged.
3. After you have a subscription, sign into the Azure portal at http://portal.azure.com.
4. Add a Microsoft Translator API subscription to your Azure account.
Select the ﻿+ New ﻿option.
Select ﻿Intelligence﻿ from the list of services.
Select ﻿Cognitive Services APIs﻿.
Select the ﻿API Type﻿ option.
﻿Select ﻿Text Translation
In the ﻿Pricing Tier﻿ section, select the Free Tier (F0)
Fill out the rest of the form, and press the ﻿Create﻿ button.
5. Retrieve your authentication key.
Go to ﻿All Resources﻿ and select the Microsoft Translator API you subscribed to.
Go to the ﻿Keys ﻿option and copy your subscription key to access the service.

##Limitations
* Does not handle String Arrays so far
* Does not handle CDATA
Keep your strings simple, and this will work just fine. For any issues, post on my GitHub. Or send a pull request.

##Credits
Fork of original code by Süleyman Yılmaz at https://github.com/e1631225

##License
This program is Android Auto-Localization
Copyright (C) 2017  Akhil Kedia

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.