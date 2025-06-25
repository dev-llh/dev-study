import "./App.css"
import MobileHeader from "./components/MobileHeader"
import SearchForm from "./components/SearchForm"
import ResultsList from "./components/ResultsList"
import { ThemeProvider } from "./contexts/ThemeContext"
import { useState } from "react"

function App() {
  const [searchQuery, setSearchQuery] = useState("")
  const [searchResults, setSearchResults] = useState([])
  const [isLoading, setIsLoading] = useState(false)

  const mockWhiskeys = [
    {
      id: "w1",
      name: "Lagavulin 16",
      distillery: "Lagavulin",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 16,
      abv: 43,
      price: 95000,
      rating: 4.7,
      flavor_profile: ["스모키", "피트", "바다향", "달콤함", "바닐라"],
      description: "아일라 지역의 대표적인 위스키로, 강한 피트와 스모키한 향이 특징입니다.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Lagavulin+16"
    },
    {
      id: "w2",
      name: "Highland Park 12",
      distillery: "Highland Park",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 12,
      abv: 40,
      price: 65000,
      rating: 4.5,
      flavor_profile: ["가벼운 스모크", "꿀", "오렌지", "스파이시"],
      description: "오크니 섬에서 생산되는 균형 잡힌 위스키로, 가벼운 스모크와 꿀, 오렌지 향이 조화롭게 어우러져 있습니다.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Highland+Park+12"
    },
    {
      id: "w3",
      name: "Talisker 10",
      distillery: "Talisker",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 10,
      abv: 45.8,
      price: 70000,
      rating: 4.4,
      flavor_profile: ["해풍", "후추", "스모키", "과일"],
      description: "스카이 섬의 유일한 증류소에서 생산되는 위스키로, 해풍과 후추 향이 특징적입니다.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Talisker+10"
    },
    {
      id: "w4",
      name: "Ardbeg 10",
      distillery: "Ardbeg",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 10,
      abv: 46,
      price: 85000,
      rating: 4.6,
      flavor_profile: ["강한 피트", "스모키", "시트러스", "초콜릿"],
      description: "아일라 지역의 강한 피트 향이 특징인 위스키로, 스모키한 향과 함께 시트러스와 초콜릿 향이 느껴집니다.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Ardbeg+10"
    }
  ]

  const handleSearch = async (query) => {
    setSearchQuery(query)
    setIsLoading(true)
    try {
      // 검색어와 매칭되는 위스키 찾기
      const filteredResults = mockWhiskeys.filter(whiskey => {
        const searchTerms = query.toLowerCase()
        return (
          whiskey.name.toLowerCase().includes(searchTerms) ||
          whiskey.distillery.toLowerCase().includes(searchTerms) ||
          whiskey.country.toLowerCase().includes(searchTerms) ||
          whiskey.type.toLowerCase().includes(searchTerms) ||
          whiskey.flavor_profile.some(flavor => flavor.toLowerCase().includes(searchTerms)) ||
          whiskey.description.toLowerCase().includes(searchTerms)
        )
      })
      setSearchResults(filteredResults)
    } catch (error) {
      console.error("Search failed:", error)
      setSearchResults([])
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <ThemeProvider>
      <div className="app">
        <MobileHeader />
        <main className="main-content">
          <ResultsList results={searchResults} query={searchQuery} isLoading={isLoading} />
        </main>
        <SearchForm onSearch={handleSearch} />
      </div>
    </ThemeProvider>
  )
}

export default App
