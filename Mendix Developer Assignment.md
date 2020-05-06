# Mendix Developer Assignment

You are a developer for a website collecting and publishing recipes. The task assigned to you is to create the back-end REST services which can be consumed by the web designers.

Because Java is the common language in the company we'll expect a java solution, the tools, library and build system are your choice as you are the Java expert. The recipe web site will be created using the Mendix Desktop Modeler and consume your back-end service. 

Below you'll find the user stories the product owner defined to create the first version of the Recipe web site. 

We expect your work to be delivered as Git repo for the java solution. It's up to you to share a repo or wrap everything in a zip file (including history). For the Mendix project, please use the [Team collaboration](https://docs.mendix.com/developerportal/collaborate/team) to invite the Mendixites who will be conducting the interview. Please also [**commit**](https://docs.mendix.com/refguide/version-control-concepts) your project. 


### Story 1 - *As web designer I would like to query recipes from the back-end system with the capability to query subsets so I don't get all recipes at once*


URL: /services/recipe/all  
HTTP Method: GET

HTTP response codes:

| Code | Reason |
| ---- | ------ |
| 200  | Successful operation |
| 204  | No recipes found |
| 500  | Internal error occurred |


HTTP 200 : List of recipes  
MimeTypes: application/json

Body: 

```
{
  "results": 1,
  "total": 90,
  "recipes": [
    {
      "head": {
        "title": "30 Minute Chili",
        "categories": {
          "cat": [
            "Main dish",
            "Chili"
          ]
        },
        "yield": "6"
      },
      "ingredients": {
        "ing": [
          {
            "amt": {
              "qty": "1",
              "unit": "pound"
            },
            "item": "Ground chuck or lean ground; beef"
          },
          {
            "amt": {
              "qty": "1",
              "unit": ""
            },
            "item": "Onion; large, chopped"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "can"
            },
            "item": "Kidney beans; (12 oz)"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "can"
            },
            "item": "Tomato soup; undiluted"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "teaspoon"
            },
            "item": "Salt"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "tablespoon"
            },
            "item": "Chili powder; (or to taste)"
          },
          {
            "amt": {
              "qty": "",
              "unit": ""
            },
            "item": "Hot pepper sauce; to taste"
          }
        ]
      },
      "directions": {
        "step": "  Brown the meat in a little butter and cook until the meat is brown -- about\n  10 minutes. Add all other ingredients and let simmer for 30 minutes. Your\n  choice of hot sauce may be added to taste.\n  \n  Recipe by: MasterCook Archives\n  \n  Posted to recipelu-digest Volume 01 Number 577 by Rodeo46898\n  <Rodeo46898@aol.com> on Jan 22, 1998\n \n"
      }
    }
  ]
}
```

### Story 2 - *As web designer I would like to query all recipe categories from the back-end so I provide an easy selection mechanism on the web site*


URL: /services/recipe/filter/categories  
HTTP Method: GET

HTTP response codes:

| Code | Reason |
| ---- | ------ |
| 200  | Successful operation |
| 204  | No categories found |
| 500  | Internal error occurred |


HTTP 200 : List of recipes  
MimeTypes: application/json

Body

```
{
  "results": 7,
  "categories": [
    "chili",
    "cake mixes",
    "main dish",
    "cakes",
    "liquor",
    "vegetables",
    "microwave"
  ]
}
```

### Story 3 - *As a web designer I would like to have an endpoint on the back-end to add new recipes to the store*

URL: /services/recipe/add  
HTTP Method: PUT

HTTP response codes:

| Code | Reason |
| ---- | ------ |
| 201  | Recipe created |
| 400  | Wrong JSON object
| 409  | Recipe duplicated |
| 500  | Internal error occurred |

Request body

```
{
  "head": {
    "title": "Amaretto Cake",
    "categories": {
      "cat": [
        "Liquor",
        "Cakes",
        "Cake mixes"
      ]
    },
    "yield": "1"
  },
  "ingredients": {
    "ing-div": [
      {
        "title": "",
        "ing": [
          {
            "amt": {
              "qty": "1 1\/2",
              "unit": "cups"
            },
            "item": "Toasted Almonds; chopped"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "package"
            },
            "item": "Yellow cake mix; no pudding"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "package"
            },
            "item": "Vanilla instant pudding"
          },
          {
            "amt": {
              "qty": "4",
              "unit": ""
            },
            "item": "Eggs"
          },
          {
            "amt": {
              "qty": "1\/2",
              "unit": "cups"
            },
            "item": "Vegetable oil"
          },
          {
            "amt": {
              "qty": "1\/2",
              "unit": "cups"
            },
            "item": "Water"
          },
          {
            "amt": {
              "qty": "1\/2",
              "unit": "cups"
            },
            "item": "Amaretto"
          },
          {
            "amt": {
              "qty": "1",
              "unit": "teaspoon"
            },
            "item": "Almond extract"
          }
        ]
      },
      {
        "title": "GLAZE",
        "ing": [
          {
            "amt": {
              "qty": "1\/2",
              "unit": "cups"
            },
            "item": "Sugar"
          },
          {
            "amt": {
              "qty": "1\/4",
              "unit": "cups"
            },
            "item": "Water"
          },
          {
            "amt": {
              "qty": "2",
              "unit": "tablespoons"
            },
            "item": "Butter"
          },
          {
            "amt": {
              "qty": "1\/4",
              "unit": "cups"
            },
            "item": "Amaretto"
          },
          {
            "amt": {
              "qty": "1\/2",
              "unit": "teaspoons"
            },
            "item": "Almond extract"
          }
        ]
      }
    ]
  },
  "directions": {
    "step": "  Sprinkle 1 cup almonds into bottom of a well-greased       and floured 10\"\n  tube pan; set aside. Combine cake mix,     pudding mix, eggs, oil, water,\n  amaretto, and almond        extract in a mixing bowl; beat on low speed of\n  an\n         electric mixer til dry ingredients are moistened.          Increase\n  speed to medium, and beat 4 minutes. Stir in      remaining 1\/2 cup\n  almonds. Pour batter into prepared       tube pan. Bake at 325~ for 1 hour\n  or til a wooden pick inserted in center comes out clean. Cool cake in pan\n    10-15 minutes; remove from pan, and cool completely.       Combine sugar,\n  water, and butter in a small saucepan;      bring to a boil. Reduce heat to\n  medium and gently boil     4-5 minutes, stirring occasionally, until sugar\n  dissolves. Remove from heat, and cool 15 minutes. Stir\n  in amaretto and almond extract. Punch holes in top of      cake with a\n  wooden pick; slowly spoon glaze on top of cake, allowing glaze to absorb in\n  cake.\n  Posted to MC-Recipe Digest V1 #263\n  \n  Date: Sun, 27 Oct 1996 20:04:57 +0000\n  \n  From: Cheryl Gimenez <clgimenez@earthlink.net>\n \n"
  }
}

```
  
  

---

Unfortunatly the web designer called in sick today and your product owner really wants to show of the new web site in the board of directors. She asks you to start creating the new web site using Mendix Desktop Modeler.
 
---


### Story 4 - *As a home cook I would like to visit a web site for recipes listing me available recipes on the landing page*

The landing page of the new web site immediatly shows what the site is all about, recipes.

The page is devided into two sections, on the left all available categories are shown. The main panel contains a list of recipes just showing enought information as title and time needed (yield) to know what it the recipe is about.

The categories can be selected to query the recipe list.

The recipes are, of course, queried from the back end system.

### Story 5 - *As a home cook I want to see the recipe details so I can cook it myself*

When a recipe is selected on the landing page a new page will open showing me the details of the recipe.


