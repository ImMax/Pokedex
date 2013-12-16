<%@ page import="pokedex.PokedexDB" %>
<%@ page import="pokedex.Pokemon" %>
<%@ page import="pokedex.Element" %>
<%@ page import="java.util.*" %>
<%!
    public String outputPokemons(List<Pokemon> list) {
        String s = "<form id='tralala' action='/index.jsp' method='post'>";
        for (Pokemon pokemon : list) {
            s += "<button type='submit' name='pokemon_name' value='" + pokemon.getName() + "'><img src='/images/pokemon/ico/" + pokemon.getPicName() + "'/><br/>" + pokemon.getName() + "</button>";
        }
        s += "</form>";
        return s;
    }
%>
<%!
    public String outputElements(List<Element> list) {
        String s = "";
        for(Element e : list) {
        s += "<img class='element' src='/images/elements/" + e.getPicName() + "'/>";
        }
        return s;
    }
%>
<html>
    <link rel="stylesheet" type="text/css" href="/style/base.css">
    <head>
       <script language="Javascript" type="text/javascript">
            var options = '"><option value="name">Name</option><option value="species">Species</option><option value="ability">Ability</option><option value="type">Type</option><option value="weakness">Weakness</option><option value="gender">Gender</option><option value="level">Level</option></select>';
            <!--var input = '<input type="text" name="search">';-->
            var counter = 0;
            var limit = 10;
            function addInput(divName){
                if (counter == limit) {
                    alert("Reached adding limit " + counter + " inputs");
                } else {
                    counter++;

                    var newSelect = document.createElement('p');
                    newSelect.innerHTML = "Entry " + counter + '<select id=select' + counter + ' name="select' + counter + options <!--+ input-->;
                    var newInput = document.createElement('input');
                    newInput.setAttribute('type','text');
                    newInput.setAttribute('name','search' + counter);
                    newInput.setAttribute('class', 'main_input');

                    document.getElementById(divName).appendChild(newSelect);
                    document.getElementById(divName).appendChild(newInput);
                }
            }
        </script>
        <script>
            function checkAndSubmit(select){
                var i = 1;
                var values = new Array();
                var el = document.getElementById("select1");
                while(document.getElementById("select" + i)){
                    values[i-1] = document.getElementById("select" + i).value;
                    i++;
                }
                var unique = values.filter(function (e, i, arr) {
                                               return arr.lastIndexOf(e) === i;
                                           });
                if (values.length != unique.length)
                    alert("Filters must be unique!");
                else
                    document.getElementById(select).submit();
            }
        </script>
        <title>Pokedex</title>
    </head>
    <body>
        <a href="/"><img class="start_img" src="images/pokedex.png"/></a>
        <table class="main_table">
            <tr>
                <td class="search">
                    <input type="button" value="Add filter" onClick="addInput('filters');">
                    <form id="filters" name="input" action="/index.jsp" method="post">
                        <!-- <input class="main_input" type="text" name="name">  -->
                        <input type="button" onclick="checkAndSubmit('filters')" value="Submit">
                    </form>
                </td>

                <td class="view">
                    <%
                        String res = "Parameters:\n";
                        Map<String, String> map = new HashMap<String, String>();
                        Map<String, String[]> str = request.getParameterMap();
                        for(int i = 1; i <= str.values().size()/2; i++) {
                            res += "select" + i + ": " + Arrays.toString(str.get("select" + i));
                            map.put(Arrays.toString(str.get("select" + i)).replaceAll("\\[", "").replaceAll("\\]",""), Arrays.toString(str.get("search" + i)).replaceAll("\\[", "").replaceAll("\\]",""));
                        }
                        PokedexDB pokedexDB = PokedexDB.getInstance("jdbc:h2:pokedex");
                        List<Pokemon> list = pokedexDB.searchPokemon(map);
                    %>
                    <%= outputPokemons(list) %>
                    <!-- <img class="start_img" src="images/matreshka.png"/> -->
                </td>

                <td class="info">
                    <%
                        String nameArg = request.getParameter("pokemon_name");
                        if (nameArg == null || nameArg.isEmpty()) return;
                        Pokemon pokemon = pokedexDB.searchByName(nameArg);
                        List<Element> types = pokedexDB.searchElementById(pokemon.getElementIds());
                        List<Element> weaknesses = pokedexDB.searchElementById(pokemon.getWeaknessesIds());
                        char gender = pokemon.getGender();
                        int height =  pokemon.getHeight();
                        int weight = pokemon.getWeight();
                        String species = pokedexDB.getSpeciesById(pokemon.getSpeciesId());
                        Set<String> locations = pokedexDB.getPossibleLocations(types);
                        List<Pokemon> evols = pokedexDB.getPokemonsByEvolutionId(pokemon.getEvolutionId());
                        List<String> abilities = pokedexDB.getAbilitiesByPokemonId(pokemon.getId());
                    %>
                    <table cellpadding="10">
                        <tr>
                            <td>
                                <h1><%= pokemon.getName() %></h1>
                                <%= "<img src='/images/pokemon/pic/" + pokemon.getPicName() + "'/>" %>
                                <h2>Type:</h2>
                                <%= outputElements(types) %>
                                <h2>Weaknesses:</h2>
                                <%= outputElements(weaknesses) %>
                            </td>
                            <td>
                                <div>
                                    <b>Species: </b> <%= species %>
                                </div>
                                <div>
                                    <b>Gender: </b> <%= (gender == 'a' ? "Both" : gender == 'm' ? "Male only" : "Female only") + ", " %>
                                    <b>Height: </b> <%= height + "ft"  + ", " %>
                                    <b>Weight: </b> <%= weight + "lbs "%>
                                </div>
                                <div>
                                    <b>Locations: </b> <%= locations.toString().replace("[","").replace("]", "") %>
                                </div>
                                <div>
                                    <b>Abilities: </b> <%= abilities.toString().replace("[","").replace("]", "") %>
                                </div>
                                <b>Evolutions:</b>
                                <%= outputPokemons(evols) %>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <script>addInput('filters');</script>
    </body>
</html>