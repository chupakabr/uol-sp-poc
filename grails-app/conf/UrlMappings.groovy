class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(controller: "index", action: "index")
        "403"(controller: "login", action: "index")
        "500"(controller: "error")
        "404"(controller: "error", action: "e404")
	}
}
