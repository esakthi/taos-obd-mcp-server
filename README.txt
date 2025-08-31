Tool: Represents a specific capability of the server, corresponding to one of the underlying GraphQL queries.
Resource: The REST API endpoint (URI) that exposes the tool.
Prompt: The description of what the tool does, available via the OpenAPI (Swagger) documentation.
Here is the list of tools, resources, and prompts available in this MCP server:

1. Tool: Get Past Launches

Resource: GET /api/v1/launches/past
Prompt: "Get a list of past SpaceX launches with an optional limit."
Parameters: limit (optional number)
2. Tool: Get Past Launches Detailed

Resource: GET /api/v1/launches/past/detailed
Prompt: "Get a detailed list of past SpaceX launches with pagination."
Parameters: limit (optional number), offset (optional number)
3. Tool: Get Upcoming Launches

Resource: GET /api/v1/launches/upcoming
Prompt: "Get a list of upcoming SpaceX launches with an optional limit."
Parameters: limit (optional number)
4. Tool: Get Launch by ID

Resource: GET /api/v1/launches/{id}
Prompt: "Get detailed information about a specific launch by its ID."
Parameters: id (required string)
5. Tool: Search Launches by Mission Name

Resource: GET /api/v1/launches/search
Prompt: "Search for launches that match a given mission name."
Parameters: missionName (required string)
6. Tool: Get All Launches

Resource: GET /api/v1/launches
Prompt: "Get a list of all launches with pagination and sorting options."
Parameters: limit (optional number), offset (optional number), sort (optional string), order (optional string: 'ASC' or 'DESC')
You can explore these interactively by running the server and accessing the Swagger UI at the /swagger-ui.html endpoint.
