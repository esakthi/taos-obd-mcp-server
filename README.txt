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


spacex launches static content for RAG application
A static dataset for a Retrieval-Augmented Generation (RAG) application can be created using structured and unstructured information about SpaceX launches. This information provides the factual basis for an LLM to generate accurate and detailed responses to user queries. 
Here are sample documents in JSON and plain text formats, detailing recent and historic SpaceX missions.
Sample 1: Recent Starlink mission (JSON format)
This structured data provides key facts about a specific, recent launch. This format is ideal for direct retrieval by a RAG system and can be stored in a vector database. 
json
[
  {
    "mission_name": "Starlink 10-57",
    "mission_date": "September 5, 2025",
    "payload": "28 Starlink satellites",
    "rocket": "Falcon 9 Block 5",
    "booster": "B1069",
    "launch_location": "Cape Canaveral Space Force Station, Florida",
    "mission_outcome": "Successful",
    "details": "This mission marked the 500th successful landing of a Falcon rocket booster. The Falcon 9 first stage landed on the drone ship 'Just Read the Instructions' in the Atlantic Ocean, highlighting SpaceX's focus on reusability.",
    "milestones": [
      "500th Falcon rocket landing",
      "Continued deployment of the Starlink mega-constellation"
    ]
  }
]
Use code with caution.

 
Sample 2: Starship flight test (JSON format)
Another structured example, suitable for a database, focusing on a specific Starship test flight.
json
[
  {
    "mission_name": "Starship Flight 10",
    "mission_date": "Early September 2025",
    "payload": "Test payload",
    "rocket": "Starship",
    "launch_location": "Starbase, Boca Chica, Texas",
    "mission_outcome": "Successful (achieved mission objectives)",
    "details": "Starship's tenth flight test wrapped up after a historic splashdown in the Indian Ocean. The test demonstrated critical capabilities but also highlighted areas for further development, including heat shield performance and precise orbital insertion.",
    "milestones": [
      "First successful Indian Ocean splashdown",
      "Important data collected on heat shield integrity",
      "Progress towards crewed lunar and Mars ambitions"
    ]
  }
]
Use code with caution.

 
Sample 3: Historic Dragon mission (plain text)
This unstructured format is suitable for documents or articles that can be chunked and indexed for retrieval. It provides a more narrative and historical context. 
Document Title: The Resumption of U.S. Human Spaceflight with SpaceX
Content:
"On May 30, 2020, SpaceX's Crew Dragon spacecraft, launched atop a Falcon 9 rocket, restored human spaceflight capabilities to the United States after a nearly decade-long gap. Carrying NASA astronauts, this second demonstration mission to the International Space Station (ISS) was a historic milestone for commercial spaceflight. Following this success, NASA certified the Falcon 9 and Crew Dragon system for regular crewed missions, marking the first time a private company achieved such a designation. The certification enabled SpaceX to begin flying astronauts for the Commercial Crew Program and solidified its role as a key partner for NASA." 
Sample 4: Falcon Heavy mission (plain text)
This unstructured document highlights a notable Falcon Heavy launch, emphasizing its heavy-lift capability. 
Document Title: Jupiter-3 Mission and Falcon Heavy's Heavyest Payload
Content:
"The launch of the Jupiter-3 satellite on July 29, 2023, by a Falcon Heavy rocket, was a significant event. The Jupiter-3, weighing 9,200 kg, became the heaviest payload ever launched by SpaceX to a geostationary transfer orbit (GTO). This mission showcased the Falcon Heavy's capacity for complex and heavy-lift missions, including launching large commercial satellites to high-energy orbits. This capability is critical for supporting the growing demand for advanced communication networks and other large-scale space infrastructure." 
How this content works for a RAG application
Vectorization: Text content from all samples is processed into vector embeddings. JSON data can be indexed using the mission_name or mission_date as metadata.
Retrieval: When a user asks a question like "Tell me about the 500th Falcon landing," the RAG system retrieves the relevant JSON document and its vector embedding.
Generation: The LLM receives the user's query and the retrieved content. It uses this information to formulate a detailed and accurate response, referencing the specific mission, booster, and achievement.
Benefits: This approach allows the application to provide current and accurate information about SpaceX launches, overcoming the knowledge cutoff of the underlying LLM. Continuous updates of the knowledge base are also enabled without retraining the LLM. 
