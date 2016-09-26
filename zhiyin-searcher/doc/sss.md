curl -XPUT 112.126.92.1:9200/contents -d ‘{ 
“settings”: { 
“index.store.type”: “niofs”, 
“index.refresh_interval”:”10s” 
} 
}’;