"use client"

import { useState } from "react"
import { Menu, User, Sun, Moon } from "lucide-react"
import { useTheme } from "../contexts/ThemeContext"
import "./MobileHeader.css"

const MobileHeader = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const { theme, toggleTheme } = useTheme()

  const menuItems = [
    { name: "홈", icon: "🏠" },
    { name: "인기 위스키", icon: "🔥" },
    { name: "위스키 종류", icon: "🥃" },
    { name: "내 컬렉션", icon: "❤️" },
    { name: "설정", icon: "⚙️" },
  ]

  return (
    <>
      <header className="mobile-header">
        <div className="header-content">
          <div className="header-left">
            <button className="btn btn-ghost" onClick={() => setIsMenuOpen(true)}>
              <Menu size={20} />
            </button>
            <h1 className="app-title gradient-text">위스키 VAVA</h1>
          </div>

          <div className="header-right">
            <button className="btn btn-ghost">
              <User size={20} />
            </button>
          </div>
        </div>

      </header>

      {/* 사이드 메뉴 */}
      {isMenuOpen && (
        <div className="menu-overlay" onClick={() => setIsMenuOpen(false)}>
          <div className="side-menu" onClick={(e) => e.stopPropagation()}>
            <div className="menu-header">
              <h2 className="gradient-text">위스키 VAVA</h2>
              <p>내 취향에 딱 맞는 위스키 찾기</p>
            </div>

            <nav className="menu-nav">
              <div className="menu-section-title">메뉴</div>
              {menuItems.map((item) => (
                <a key={item.name} href="#" className="menu-item">
                  <span className="menu-icon">{item.icon}</span>
                  <span>{item.name}</span>
                </a>
              ))}
            </nav>

            <div className="menu-footer">
              <div className="menu-footer-content">
                <span>© 2025 위스키 VAVA</span>
                <button className="btn btn-ghost" onClick={toggleTheme}>
                  {theme === "dark" ? <Sun size={16} /> : <Moon size={16} />}
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  )
}

export default MobileHeader
