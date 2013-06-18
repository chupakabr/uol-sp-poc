import com.uol.seriousparachute.HTTP403Exception

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(controller: "index", action: "index")
        "403"(controller: "login", action: "index")
        "403"(controller: "login", action: "index", exception: HTTP403Exception)
        "500"(controller: "login", action: "index", exception: HTTP403Exception)
        "500"(controller: "error")
        "404"(controller: "error", action: "e404")
	}
}
