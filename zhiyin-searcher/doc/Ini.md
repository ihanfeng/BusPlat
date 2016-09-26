
curl -XPOST http://112.126.92.1:9200/contents/content/_mapping -d'
{
    "content": {
             "_all": {
             "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
  "roleId": {
                "type": "long"
            },
            "name": {
                "type": "string",
                "store": "no",
                "term_vector": "with_positions_offsets",
                "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
                "include_in_all": "true",
                "boost": 8
            },
"title": {
                "type": "string",
                "store": "no",
                "term_vector": "with_positions_offsets",
              "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
                "include_in_all": "true",
                "boost": 8
            },
            "document": {
                "type": "string",
                "store": "no",
                "term_vector": "with_positions_offsets",
               "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
                "include_in_all": "true",
                "boost": 8
            }
,
            "description": {
                "type": "string",
                "store": "no",
                "term_vector": "with_positions_offsets",
               "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
                "include_in_all": "true",
                "boost": 8
            }
        }
    }
}'
