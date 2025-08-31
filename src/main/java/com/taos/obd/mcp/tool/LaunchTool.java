package com.taos.obd.mcp.tool;

import com.taos.obd.mcp.dto.*;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class LaunchTool {

    private final GraphQlClient graphQlClient;

    public LaunchTool(GraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    public List<Launch> getPastLaunches(Integer limit) {
        // language=GraphQL
        String query = """
            query GetPastLaunches($limit: Int) {
              launchesPast(limit: $limit) {
                mission_name
                launch_date_local
                launch_site {
                  site_name_long
                }
                links {
                  article_link
                  video_link
                }
                rocket {
                  rocket_name
                }
              }
            }
        """;

        GetPastLaunchesQueryResponse response = graphQlClient.document(query)
                .variable("limit", limit)
                .execute()
                .map(r -> r.toEntity(GetPastLaunchesQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launchesPast();
    }

    public List<Launch> getPastLaunchesDetailed(Integer limit, Integer offset) {
        // language=GraphQL
        String query = """
            query GetPastLaunchesDetailed($limit: Int, $offset: Int) {
              launchesPast(limit: $limit, offset: $offset) {
                id
                mission_name
                launch_date_local
                launch_date_utc
                launch_year
                launch_success
                details
                launch_site {
                  site_id
                  site_name
                  site_name_long
                }
                links {
                  mission_patch
                  mission_patch_small
                  article_link
                  video_link
                  wikipedia
                  youtube_id
                  flickr_images
                }
                rocket {
                  rocket_id
                  rocket_name
                  rocket_type
                  first_stage {
                    cores {
                      core_serial
                      flight
                      reused
                      land_success
                      landing_type
                    }
                  }
                  second_stage {
                    payloads {
                      payload_id
                      customers
                      nationality
                      manufacturer
                      payload_type
                      payload_mass_kg
                      orbit
                    }
                  }
                }
              }
            }
        """;

        GetPastLaunchesDetailedQueryResponse response = graphQlClient.document(query)
                .variables(Map.of("limit", limit, "offset", offset))
                .execute()
                .map(r -> r.toEntity(GetPastLaunchesDetailedQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launchesPast();
    }

    public List<Launch> getUpcomingLaunches(Integer limit) {
        // language=GraphQL
        String query = """
            query GetUpcomingLaunches($limit: Int) {
              launchesUpcoming(limit: $limit) {
                id
                mission_name
                launch_date_local
                launch_date_utc
                launch_site {
                  site_name_long
                }
                rocket {
                  rocket_name
                }
                details
              }
            }
        """;

        GetUpcomingLaunchesQueryResponse response = graphQlClient.document(query)
                .variable("limit", limit)
                .execute()
                .map(r -> r.toEntity(GetUpcomingLaunchesQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launchesUpcoming();
    }

    public Launch getLaunchById(String id) {
        // language=GraphQL
        String query = """
            query GetLaunchById($id: ID!) {
              launch(id: $id) {
                id
                mission_name
                launch_date_local
                launch_date_utc
                launch_year
                launch_success
                details
                upcoming
                launch_site {
                  site_id
                  site_name
                  site_name_long
                }
                links {
                  mission_patch
                  article_link
                  video_link
                  wikipedia
                  youtube_id
                  flickr_images
                }
                rocket {
                  rocket_id
                  rocket_name
                  rocket_type
                  first_stage {
                    cores {
                      core_serial
                      flight
                      reused
                      land_success
                    }
                  }
                  second_stage {
                    payloads {
                      payload_id
                      customers
                      payload_type
                      payload_mass_kg
                      orbit
                    }
                  }
                }
              }
            }
        """;

        GetLaunchByIdQueryResponse response = graphQlClient.document(query)
                .variable("id", id)
                .execute()
                .map(r -> r.toEntity(GetLaunchByIdQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launch();
    }

    public List<Launch> searchLaunchesByMission(String missionName) {
        // language=GraphQL
        String query = """
            query SearchLaunchesByMission($missionName: String!) {
              launchesByMissionName(missionName: $missionName) {
                id
                mission_name
                launch_date_local
                launch_success
                launch_site {
                  site_name_long
                }
                rocket {
                  rocket_name
                }
              }
            }
        """;

        SearchLaunchesByMissionQueryResponse response = graphQlClient.document(query)
                .variable("missionName", missionName)
                .execute()
                .map(r -> r.toEntity(SearchLaunchesByMissionQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launchesByMissionName();
    }

    public List<Launch> getAllLaunches(Integer limit, Integer offset, String sort, Order order) {
        // language=GraphQL
        String query = """
            query GetAllLaunches($limit: Int, $offset: Int, $sort: String, $order: Order) {
              launches(limit: $limit, offset: $offset, sort: $sort, order: $order) {
                id
                mission_name
                launch_date_local
                launch_success
                upcoming
                rocket {
                  rocket_name
                }
              }
            }
        """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("limit", limit);
        variables.put("offset", offset);
        variables.put("sort", sort);
        variables.put("order", order);

        GetAllLaunchesQueryResponse response = graphQlClient.document(query)
                .variables(variables)
                .execute()
                .map(r -> r.toEntity(GetAllLaunchesQueryResponse.class))
                .block();

        return Objects.requireNonNull(response).launches();
    }
}
