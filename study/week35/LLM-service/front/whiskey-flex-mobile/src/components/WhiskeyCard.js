"use client"

import { useState } from "react"
import { Star, Heart, Info } from "lucide-react"
import WhiskeyModal from "./WhiskeyModal"
import "./WhiskeyCard.css"

const WhiskeyCard = ({ whiskey, rank }) => {
  const [liked, setLiked] = useState(false)
  const [showModal, setShowModal] = useState(false)

  const handleLike = (e) => {
    e.preventDefault()
    e.stopPropagation()
    setLiked(!liked)
  }

  const handleShare = (e) => {
    e.preventDefault()
    e.stopPropagation()
    alert("공유 기능은 준비 중이에요!")
  }

  return (
    <>
      <div className="whiskey-card card fade-in-up">
        <div className="card-content">
          <div className="card-image">
            <img
              src={
                whiskey.image_url ||
                `https://via.placeholder.com/120x160/333/fff?text=${encodeURIComponent(whiskey.name)}`
              }
              alt={whiskey.name}
              className="whiskey-image"
            />
            <div className="rank-badge badge">#{rank}</div>
          </div>

          <div className="card-info">
            <div className="card-header">
              <div className="whiskey-info">
                <h3 className="whiskey-name">{whiskey.name}</h3>
                <p className="whiskey-origin">
                  {whiskey.distillery}, {whiskey.country}
                </p>
              </div>

              <div className="rating-badge">
                <Star size={12} className="star-icon" />
                <span>{whiskey.rating.toFixed(1)}</span>
              </div>
            </div>

            <div className="flavor-tags">
              {whiskey.flavor_profile.slice(0, 2).map((flavor) => (
                <span key={flavor} className="flavor-tag badge">
                  {flavor}
                </span>
              ))}
              {whiskey.flavor_profile.length > 2 && (
                <span className="flavor-tag badge">+{whiskey.flavor_profile.length - 2}</span>
              )}
            </div>

            <div className="card-footer">
              <div className="price-info">
                <span className="price gradient-text">₩{whiskey.price.toLocaleString()}</span>
                <span className="abv">{whiskey.abv}%</span>
              </div>

              <div className="card-actions">
                <button className={`btn btn-ghost ${liked ? "liked" : ""}`} onClick={handleLike}>
                  <Heart size={14} />
                </button>
                <button className="btn btn-ghost" onClick={() => setShowModal(true)}>
                  <Info size={14} />
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <WhiskeyModal
        whiskey={whiskey}
        isOpen={showModal}
        onClose={() => setShowModal(false)}
        liked={liked}
        onLike={handleLike}
        onShare={handleShare}
      />
    </>
  )
}

export default WhiskeyCard
