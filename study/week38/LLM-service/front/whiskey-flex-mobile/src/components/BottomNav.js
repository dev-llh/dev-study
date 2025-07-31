import { Home, Search, Heart, User } from "lucide-react"
import "./BottomNav.css"

const BottomNav = () => {
  const items = [
    {
      label: "홈",
      icon: Home,
      href: "#",
      active: true,
    },
    {
      label: "탐색",
      icon: Search,
      href: "#",
    },
    {
      label: "저장",
      icon: Heart,
      href: "#",
    },
    {
      label: "프로필",
      icon: User,
      href: "#",
    },
  ]

  return (
    <div className="bottom-nav">
      <nav className="bottom-nav-content">
        {items.map((item) => (
          <a key={item.label} href={item.href} className={`nav-item ${item.active ? "active" : ""}`}>
            <item.icon size={20} className="nav-icon" />
            <span className="nav-label">{item.label}</span>
          </a>
        ))}
      </nav>
    </div>
  )
}

export default BottomNav
