"use client"

import "./App.css"
import MobileHeader from "./components/MobileHeader"
import SearchForm from "./components/SearchForm"
import ResultsList from "./components/ResultsList"
import { ThemeProvider } from "./contexts/ThemeContext"
import { useState } from "react"
import axios from "axios"

function App() {
  const [searchQuery, setSearchQuery] = useState("")
  const [searchResults, setSearchResults] = useState("")
  const [isLoading, setIsLoading] = useState(false)

  const handleSearch = async (query) => {
    setSearchQuery(query)
    setIsLoading(true)
    setSearchResults("") // 이전 결과 초기화

    try {
      // 검색어와 매칭되는 위스키 찾기
      const response = await axios.get("http://localhost:8000/recommend/test", {
        params: { query },
      })

      setSearchResults(response.data.answer)
    } catch (error) {
      console.error("Search failed:", error)
      setSearchResults("API 요청에 실패했습니다. 나중에 다시 시도해주세요.")
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
